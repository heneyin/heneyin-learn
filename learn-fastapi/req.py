import requests

html_content = """
<html>
  <head>
    <meta charset="utf-8">
    <style>
      body { margin: 2rem; font-family: Arial; }
    </style>
  </head>
  <body>
    <h1>Hello from dynamic HTML!</h1>
    <p>这是一个测试 PDF 生成的内容。</p>
  </body>
</html>
"""

files = {
    'files': ('index.html', html_content, 'text/html')
}

# 添加页面配置参数
data = {
    "paperWidth": "8.27",
    "paperHeight": "11.7",
    "marginTop": "0.4",
    "marginBottom": "0.4",
    "marginLeft": "0.4",
    "marginRight": "0.4",
}

try:
    response = requests.post(
        'http://localhost:3000/forms/chromium/convert/html',
        files=files,
        data=data,
        timeout=30  # 防止超时
    )

    if response.status_code != 200:
        print(f"错误状态码: {response.status_code}")
        print("响应内容:", response.text)
        exit()

    if not response.content.startswith(b'%PDF'):
        print("生成的 PDF 文件损坏")
        with open("debug.html", "w") as f:
            f.write(html_content)
        exit()

    with open('output.pdf', 'wb') as f:
        f.write(response.content)
    print("PDF 生成成功！")

except Exception as e:
    print("请求异常:", str(e))
