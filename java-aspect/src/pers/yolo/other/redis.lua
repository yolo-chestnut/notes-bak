-- local max = ARGV[1] - '0'
local max = tonumber(ARGV[1])
if redis.call('exists', KEYS[1]) == 1 then
    local value = redis.call('get', KEYS[1]) - '0'
    if value <= max then
        redis.call('incr', KEYS[1])
        redis.call('expire', KEYS[1], ARGV[2])
        return 1
    end
else
    redis.call('set', KEYS[1], 1, 'EX', ARGV[2], 'NX')
    return 1
end
return 0
