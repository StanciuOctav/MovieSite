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
      email: "",
      password: "",
      info: null
    }
  },
  methods: {
    /* async login() {
      try {
        var result = await axios.post("http://localhost:8081/admin/login", { email: this.email, password: this.password });
        this.$router.push({ name: 'home' });
      } catch (e) {
        alert("Credentiale incorecte!");
      }
    } */

    login() {
      axios.get("http://localhost:8080/api/users/" + this.email, {})
          .then(response => {
            if (response.data === "") {
              if (confirm("User doesn't exist. Want to register?")) {
                this.$router.push({name: 'register'})
              }
            } else {
              console.log("User exists. Redirecting to home page")
            }
          })
          .catch(error => (console.log(error)))
    }
  }
}
</script>
