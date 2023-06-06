## Dating Website (RIZZ AI) Using ChatGPT
Runtime link: [Frontend Link](https://hetvit27.github.io/freelancer-theme) / [Backend Link](https://dating.dontntntnt.de/)

NOTE: all of the files are in this file path: [/src/main/java/com/nighthawk/spring_portfolio/database/chat/](https://github.com/dontran15/rizzai-backend/tree/master/src/main/java/com/nighthawk/spring_portfolio/database/chat)

### GPT Documentation
#### Getting the API Key
The most important thing is to get the API key from OpenAI. This can be done through an Open AI account and generating a key. Note that the key won't allow you to use the actual command prompts and will instead send error messages that you have "overused" the key. This is because you need to pay for the tokens to actually call the API. Luckily, OpenAI allows you to sign up for $5 of free credit which is roughly 2 million tokens (tokens being words basically). Each different chatbot has different rates though, but the number of credits for an individual use is more than sufficient.

#### Backend
There are various documentation pieces on API implementations of ChatGPT. Unfortunately for Java users, most documentation is in Python. There is a few sites with Java documentation but we never got the chance to find a working documentation for GPT 3.5-Turbo. Instead, we have an API call to Davinci (which has less complex responses and sentiment analysis when reading prompts) and a Python call to GPT 3.5-Turbo.

##### Key Components:
- Chat.java: 
