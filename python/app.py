import asyncio
import datetime
import json

from flask import Flask, request
from telethon import TelegramClient

from config import API_NAME, API_ID, API_HASH

app = Flask(__name__)


@app.route('/getOpportunities')
def get_opportunities():
    channel = request.args.get('channel', 'tviyspace', str)
    q = request.args.get('q', 1, int)

    loop = asyncio.new_event_loop()
    asyncio.set_event_loop(loop)

    client = TelegramClient(API_NAME, API_ID, API_HASH)
    client.start()
    client.connect()

    messages = []
    for message in client.iter_messages(channel, q):
        messages.append(OpportunityDto(message.id, message.message, message.date).__dict__)

    client.disconnect()

    return app.response_class(response=json.dumps(messages, cls=DateTimeEncoder), mimetype='application/json')


class OpportunityDto:
    def __init__(self, post_id: int, content: str, created_at: datetime.datetime):
        self.post_id = post_id
        self.content = content
        self.created_at = created_at


class DateTimeEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, datetime.datetime):
            return o.isoformat()
        return json.JSONEncoder.default(self, o)


if __name__ == '__main__':
    app.run()

# @client.on(events.NewMessage(chats=[123123]))
# async def handler(event):
#     print("Event Occurred")
#     print(event.raw_text)
