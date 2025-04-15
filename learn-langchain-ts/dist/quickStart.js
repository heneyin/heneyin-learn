import { ChatOpenAI } from "@langchain/openai";
const chatModel = new ChatOpenAI({
    apiKey: "sk-",
});
await chatModel.invoke("what is LangSmith?");
console.log('hello world');
