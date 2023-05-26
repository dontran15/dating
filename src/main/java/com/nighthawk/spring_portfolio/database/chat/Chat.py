import requests

api_key = "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";

def send_message(message):
    headers = {
        "Content-Type": "application/json",
        "Authorization": f"Bearer {api_key}"
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

    return response_json

message = "is this query about love advice? 'how to make a cake'"
response = send_message(message)

print(response['choices'][0]['message']['content'])