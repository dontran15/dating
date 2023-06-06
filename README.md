## Dating Website (RIZZ AI) Using ChatGPT
Runtime link: [Frontend Link](https://hetvit27.github.io/freelancer-theme) / [Backend Link](https://dating.dontntntnt.de/)

NOTE: all of the files are in this file path: [/src/main/java/com/nighthawk/spring_portfolio/database/chat/](https://github.com/dontran15/rizzai-backend/tree/master/src/main/java/com/nighthawk/spring_portfolio/database/chat)

### GPT Documentation
#### Getting the API Key
The most important thing is to get the API key from OpenAI. This can be done through an Open AI account and generating a key. Note that the key won't allow you to use the actual command prompts and will instead send error messages that you have "overused" the key. This is because you need to pay for the tokens to actually call the API. Luckily, OpenAI allows you to sign up for $5 of free credit which is roughly 2 million tokens (tokens being words basically). Each different chatbot has different rates though, but the number of credits for an individual use is more than sufficient.

#### Backend
There are various documentation pieces on API implementations of ChatGPT. Unfortunately for Java users, most documentation is in Python. There is a few sites with Java documentation but we never got the chance to find a working documentation for GPT 3.5-Turbo. Instead, we have an API call to Davinci (which has less complex responses and sentiment analysis when reading prompts) and a Python call to GPT 3.5-Turbo.

##### Key Components:
- Chat.java: A class with static methods that handles all requests to GPT. For basic implementations of Davinci (and likely GPT 3.5-Turbo if we can get that working), the most important function is daVinciTest(String text){}. _text_ is a parameter that passes in the user prompt, it can be whatever the user inputs. 
- OpenAI API Request, URL: "https://api.openai.com/v1/completions" (this is the url you pass through for Davinci. The url for GPT 3.5 is different though. As part of your request, you pass in a JSON with the model, prompt (which we pass _text_ into), and max tokens that the user can pass through, and temperature (the randomness of the GPT response). Then a Buffered Reader is used to await for the response, before sending the response in the return. Check tester method to play around with it.
- Python implementation: Python documentation is a lot more established and therefore was easier to actually access gpt-turbo 3.5. We use a file reader for the Python file Chat.py (which basically does the same API request as the Java implentations noted above) and use a buffered reader to get the response after calling and running the file.
- Spring API Request: After this, we can handle all other responses based on how we want our backend features using GPT to be. General Spring API request is all that's needed.

#### Deployment and Running Locally: (IMPORTANT)
In order to run ChatGPT AI locally, you need to pass in the key as one of the properties in the header. OpenAI however, reads to see if your key is exposed and promptly closes the key if it's ever in plaintext in a Github repo. As such, to run it locally, both on AWS and on your own local machine, we found creating a .txt file with the key in plaintext (or encrypted if you so choose), and then putting it into .gitignore, allows you to prevent the key from being pushed while still being able to access it on your repo. We use a file reader in Java to read the value (the API key) of this .txt file.

