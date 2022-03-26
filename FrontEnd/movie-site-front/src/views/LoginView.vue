<template>
  <v-form
      ref="form"
      class="ml-6 mr-6"
  >
    <v-text-field
        v-model="email"
        label="Email"
        required
    ></v-text-field>

    <v-text-field
        v-model="password"
        label="Password"
        type="password"
        required
    ></v-text-field>

    <v-btn
        color="success"
        class="mr-10"
        @click="login"
    >
      Login
    </v-btn>

    <v-btn
        color="error"
        class="mr-4"
        @click="$router.push({ name: 'register' })"
    >
      Register
    </v-btn>
    <div>
      {{ info }}
    </div>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  name: 'Login',
  data() {
    return {
      bctx: "http://localhost:8080",
      fctx: window.location.protocol + "//" + window.location.host,
      email: "",
      password: "",
      info: null
    }
  },
  methods: {
    login() {
      let request = `/api/users/user?userEmail=${this.email}&userPassword=${this.password}`
      axios.get(this.bctx + request)
          .then(response => {
            if (response.data === "") {
              if (confirm("User doesn't exist. Want to register?")) {
                this.$router.push({name: 'register'})
              }
            } else {
              window.location.href = this.fctx + "/home"
            }
          })
          .catch(error => (console.log(error)))
    }
  }
}
</script>
