local key, rate, refillTime, limit, interval = KEYS[1], tonumber(ARGV[1]), tonumber(ARGV[2]), tonumber(ARGV[3]), tonumber(ARGV[4])
local counter = redis.call('hgetall', key)

if table.maxn(counter) == 0 then
    redis.call('hmset', key, 'lastRefillTime', refillTime, 'tokensRemaining', limit - 1)
    return 1
elseif table.maxn(counter) == 4 then
    local lastRefillTime, tokensRemaining = tonumber(counter[2]), tonumber(counter[4])
    local currentTokens
    if refillTime >= lastRefillTime then
        local intervalSinceLast = refillTime - lastRefillTime
        if intervalSinceLast > interval then
            currentTokens = limit
        else
            local grantedTokens = math.floor(intervalSinceLast * rate)
            currentTokens = math.min(grantedTokens + tokensRemaining, limit)
        end

        redis.call('hset', key, 'lastRefillTime', refillTime)
        if currentTokens == 0 then
            redis.call('hset', key, 'tokensRemaining', currentTokens)
            return 0
        else
            redis.call('hset', key, 'tokensRemaining', currentTokens - 1)
            return 1
        end
    end
else
    error("Size of counter is " .. table.maxn(counter) .. ", Should Be 0 or 4.")
end