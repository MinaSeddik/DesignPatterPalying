#!/bin/bash

wrk -c200 -t10 -d15s -s ./benchmark-with-auth.lua --latency http://localhost:8080

