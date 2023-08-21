"""
某些应用程序需要根据用户输入对 LLM 和其他工具进行灵活的调用链。

agent 提供对应的灵活性

代理可以访问一套工具，并根据用户输入确定使用哪些工具。

代理可以使用多种工具，并使用一个工具的输出作为下一个工具的输入。


 two main types of agents

 1. Action agents: 每一步，使用所有先前操作的输出来决定下一步操作是什么。 适合小任务
 2. Plan-and-execute agents：预先决定完整的行动顺序，然后执行所有行动，不会变更计划。 适合复杂、长时间运行的 task。需要保持长期目标和重点

 比较好的做法是两者结合，Plan-and-execute 使用 Action agents 来完成计划。

 所有 agents:https://python.langchain.com/docs/modules/agents/agent_types/

 Tools: 代理做的事情，你提供给 agent 的 tool 高度依赖你希望 agent 做什么。

 Toolkits: 一系列 tools 的集合，在一起完成一个特殊的用例。例如数据库操作。
"""