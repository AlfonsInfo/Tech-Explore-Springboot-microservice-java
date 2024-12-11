# Login Flow

1. Client - Req login /v1/login
2. Server - Verify Credential
   1. valid -> generate token, reset attempt value
   2. not valid -> increment attempt try
3. Server - Res Token (access & refresh token)
4. Client - Save Token
5. Token will be using for request

Reference : Instagram Login Process
# Page Flow

1. 