<!-- UserForm.vue -->
<template>
  <div class="login-form">
    <h3>Hi there 👋</h3>
    <p>Enter your name and email address to start the chat.</p>
    <input v-model="userName" placeholder="Enter your name"/>
    <input v-model="userEmail" placeholder="Enter your email"/>
    <button @click="submitUserInfo">Enter chat</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userName: '',
      userEmail: ''
    };
  },
  methods: {
    submitUserInfo() {
      this.$emit('userInfoSubmitted', {userName: this.userName, userEmail: this.userEmail});
    }
  }
};
</script>
<style>
.login-form {
  margin: 5rem 3rem 3rem 3rem;
}

input {
  background: rgb(246, 247, 249);
  padding: 0.75rem;
  font-size: 1rem;
  border-radius: 8px;
  width: 90%;
  margin: 0.55rem 0;
  font-weight: 500;
  outline: none;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  border: 1px solid lightgrey;
}

button {
  background: rgb(26 129 250);
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: white;
  font-weight: 500;
  outline: none;
  border: none;
  border-radius: 8px;
  padding: 0.75rem 1.5rem;
  margin-top: 1rem;
  font-size: 1.05rem;
}
</style>