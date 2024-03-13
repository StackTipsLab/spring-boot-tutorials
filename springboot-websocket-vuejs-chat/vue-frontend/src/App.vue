<template>
  <div id="app">
    <div class="chat-box">
      <UserForm v-if="!userInfoSubmitted" @userInfoSubmitted="handleUserInfoSubmitted" />
      <chat-box v-else :user="user"/>
    </div>
  </div>
</template>

<script>
import UserForm from './components/UserForm.vue';
import ChatBox from './components/ChatBox.vue'

export default {
  name: 'App',
  components: {
    UserForm,
    ChatBox
  },
  data() {
    return {
      userInfoSubmitted: false,
      user: {
        userEmail: '',
        userName: ''
      }
    };
  },
  methods: {
    handleUserInfoSubmitted(userInfo) {
      console.log("User info: " + JSON.stringify(userInfo))
      this.user = userInfo;
      this.userInfoSubmitted = true;
    }
  }
};
</script>

<style>
body {
  background: rgb(246, 247, 249);
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.chat-box {
  max-width: 550px;
  background: white;
  border-radius: 12px;
  margin: 3rem auto;
  position: relative;
  height: 800px;
  max-height: 800px;
  overflow: hidden;
}


.messages {
  overflow: auto;
  height: 715px;
  overflow-y: scroll;
  overflow-x: hidden;
}

.message {
  background: rgb(246, 247, 249);
  padding: 1rem;
  font-size: 1rem;
  margin: 0.75rem 1rem;
  border-radius: 8px;
  text-align: left;
}

.message-input-container {
  position: absolute;
  bottom: 0;
  width: 100%;
}

.message-input {
  background: rgb(246, 247, 249);
  padding: 1rem;
  font-size: 1rem;
  border-radius: 8px;
  width: 90%;
  margin-bottom: 1rem;
  margin-top: 1rem;
  border: none;
  outline: none;
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
