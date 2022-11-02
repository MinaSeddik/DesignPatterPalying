
--The following globals are optional, and if defined must be functions:
--
--global setup    -- called during thread setup
--global init     -- called when the thread is starting
--global delay    -- called to get the request delay
--global request  -- called to generate the HTTP request
--global response -- called with HTTP response data
--global done     -- called with results of run


local counter = 1
local threads = {}

token = nil
path  = "/auth"


-- called during thread setup
function setup(thread)
    thread:set("id", counter)
    thread:set("name", "thread-" .. counter)

    table.insert(threads, thread)
    counter = counter + 1

end


-- called when the thread is starting
function init(args)
    --starting index for this thread...
    --local msg = "Thread %d started"
    --print(msg:format(id))

    requests  = 0
    responses = 0
end

function delay()
    --10-50ms delay before each request
    return math.random(10, 50)
    --return 2000  -- delay 2 seconds
end

-- the request function that will run at each request
request = function()

    requests = requests + 1
    return wrk.format("GET", path)

end

-- called with HTTP response data
response = function(status, headers, body)

    if not token and status == 200 then
        token = headers["X-Token"]
        path = "/repo"
        wrk.headers["X-Token"] = token
    end

    if token and status == 200 then
        responses = responses + 1
    end

end

-- called with results of run
function done(summary, latency, requests)
    for index, thread in ipairs(threads) do
        local id        = thread:get("id")
        local requests  = thread:get("requests")
        local responses = thread:get("responses")
        local msg = "thread %d made %d requests and got %d responses"
        print(msg:format(id, requests, responses))
    end
end