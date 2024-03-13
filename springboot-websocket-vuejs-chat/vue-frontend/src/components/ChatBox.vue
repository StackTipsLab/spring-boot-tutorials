<template>
  <div>
    <div class="messages">
      <div v-for="message in messages" :key="message.id" class="message">
        <h4 style="font-weight:600;margin-bottom: 0;margin-top: 0;text-transform: capitalize;">{{ message.senderName }}</h4>
        {{ message.content }}
      </div>
    </div>

    <div class="message-input-container">
      <input v-model="newMessage" @keyup.enter="send" placeholder="Type a message..." class="message-input">
    </div>
  </div>
</template>

<script>
import WebSocketService from "../WebSocketService";

export default {
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      messages: [],
      newMessage: '',
    };
  },
  created() {
    this.fetchMessages();

    WebSocketService.connect().then(() => {
      WebSocketService.subscribe('/topic/public', message => {
        this.messages.push(message);
      });
    });
  },
  methods: {
    async fetchMessages() {
      try {
        const response = await fetch(`http://localhost:8080/messages`);
        if (response.ok) {
          this.messages = await response.json();
        } else {
          console.error('Failed to fetch messages');
        }
      } catch (error) {
        console.error('Error fetching messages:', error);
      }
    },
    send() {
      const chatMessage = {
        content: this.newMessage,
        senderEmail: this.user.userEmail,
        senderName: this.user.userName
      };

      console.log("Sending message" + chatMessage)
      WebSocketService.sendMessage("/app/sendMessage", chatMessage);
      this.newMessage = '';
    }
  },
  beforeUnmount() {
    WebSocketService.disconnect();
  }
};
</script>
