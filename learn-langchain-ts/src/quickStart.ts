
import { ChatOpenAI } from "@langchain/openai";

import pkg from 'global-tunnel-ng';
const  globalTunnel = pkg;

globalTunnel.initialize({
  host: '127.0.0.1',
  port: 7890,
});

const chatModel = new ChatOpenAI({
    apiKey: "",
});

console.log('hello world1')
await chatModel.invoke("what is LangSmith?");
console.log('hello world2')

globalTunnel.end();