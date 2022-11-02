
--The following globals are optional, and if defined must be functions:
--
--global setup    -- called during thread setup
--global init     -- called when the thread is starting
--global delay    -- called to get the request delay
--global request  -- called to generate the HTTP request
--global response -- called with HTTP response data
--global done     -- called with results of run

-- init random
math.randomseed(os.time())

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

    --print('before set', thread, thread:get('field'))
    --thread:set('field', 'value')
    --print('after set', thread, thread:get('field'))
    --
    --print("--------------", thread)
    --print("Thread Setup:", thread:get("name") )
    --
    ----json = JSON:encode(thread)
    ----print(json)
    --
    ----print("***> wrk.thread.id", wrk.thread:get("name") )
    --print("***> wrk.host", wrk.host )
    --print("***> wrk.port", wrk.port )

end


-- called when the thread is starting
function init(args)
    --starting index for this thread...

    local msg = "thread %d started"
    print(msg:format(id))

    requests  = 0
    responses = 0

    --print("id", id)
    --
    --print("args length", #args)
    --print("args[0]", args[0])
    --print("args[1]", args[1])
    --print("args[2]", args[2])
    --print("args[3]", args[3])
    --
    --
    --print("--> wrk.thread.id", wrk.thread:get("name") )
    --print("--> wrk.host", wrk.host )
    --print("--> wrk.port", wrk.port )


    --index = (id-1) * args[1]
    --index = (id-1)
    --path="/auth"


end

function delay()
    --10-50ms delay before each request
    --return math.random(10, 50)


    return 2000  -- delay 2 seconds
end

-- the request function that will run at each request
request = function()

    print("-----------------------", path)
    print("-----------------------", token)
    print("-----------------------", id)

    requests = requests + 1

    return wrk.format("GET", path)

    --print(wrk.thread:get("id"))
    --if (wrk.thread:get("token") == nil)
    --then
    --    print("inside nil")
    --
    --    return wrk.format("GET", auth_url_path)
    --else
    --    wrk.headers["X-Token"] = wrk.thread:get("token")
    --    print("inside valid token")
    --
    --    return wrk.format("GET", url_path)
    --end


    -- define the path that will search for q=%v 9%v being a random number between 0 and 1000)
    --url_path = "/repo/search?q=" .. math.random(0, 1000)
    --url_path = "/repo/search?q=" .. wrk.thread:get("id")


    -- if we want to print the path generated
    --print(url_path)

    -- Return the request object with the current URL path
    --return wrk.format("GET", url_path)
end

response = function(status, headers, body)

    print("Calling response ...", status)
    print("Calling response ...", path)

    responses = responses + 1
    if not token and status == 200 then
        token = headers["X-Token"]
        path = "/repo"
        wrk.headers["X-Token"] = token
    end

end


function done(summary, latency, requests)
    for index, thread in ipairs(threads) do
        local id        = thread:get("id")
        local requests  = thread:get("requests")
        local responses = thread:get("responses")
        local msg = "thread %d made %d requests and got %d responses"
        print(msg:format(id, requests, responses))
    end
end