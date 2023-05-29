import requests
import sys

# gets chatgpt key from local aws machine
def getKey():
    file_path = "src/main/java/com/nighthawk/spring_portfolio/database/chat/key.txt"

    file = open(file_path, "r")
    key = file.read()
    
    file.close()
    
    return key

def checkQuery(message):
    headers = {
        "Content-Type": "application/json",
        "Authorization": f"Bearer {getKey()}"
    }

    data = {
        "messages": [
            {
                "role": "system",
                "content": "You are ChatGPT, a large language model trained by OpenAI."
            },
            {
                "role": "user",
                "content": message
            }
        ],

        "model": "gpt-3.5-turbo"
    }

    endpoint_url = "https://api.openai.com/v1/chat/completions"

    response = requests.post(endpoint_url, json=data, headers=headers)
    response_json = response.json()

    print(response_json['choices'][0]['message']['content'])

# Tester
message = "is this query about love advice? 'how to make a cake'"
print(checkQuery(message))

# API Code
# message = sys.argv[1]
# checkQuery(message)