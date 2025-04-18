# -*- coding: UTF-8 -*-
from datetime import datetime
from fastapi import FastAPI
from fastapi_mcp import FastApiMCP

app = FastAPI()


@app.get("/current_datetime", operation_id="current_datetime")
def currentDatetime():
    # 获取当前时间
    now = datetime.now()

    # 格式化为字符串（默认格式）
    formatted_time = now.strftime("%Y-%m-%d %H:%M:%S")
    return {"message": formatted_time}


mcp = FastApiMCP(
    app,
    # Optional parameters
    name="当前时间",
    description="获取当前时间",
    base_url="http://localhost:8000",
)

mcp.mount()
mcp.setup_server()
