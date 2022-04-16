<template>
  <v-form ref="form" class="ml-6 mr-6" v-model="valid" lazy-validation>
    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="Email"
        required
    ></v-text-field>

    <v-text-field
        v-model="password"
        :rules="passwordRules"
        label="Password"
        type="password"
        required
    ></v-text-field>

    <v-btn :disabled="!valid" color="success" class="mr-10" @click="login">
      Login
    </v-btn>

    <v-btn
        color="error"
        class="mr-4"
        @click="$router.push({ name: 'register' })"
    >
      Register
    </v-btn>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      bctx: "http://localhost:8080",
      valid: true,
      email: "",
      password: "",
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) =>
            /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
                v
            ) || "E-mail must be valid",
      ],
      passwordRules: [(v) => !!v || "Password is required"],
    };
  },
  methods: {
    validate() {
      return this.$refs.form.validate();
    },
    login() {
      if (this.validate()) {
        axios
            .post(this.bctx + "/login", {
              email: this.email,
              password: this.password,
            })
            .then(() => {
              this.$router.push({name: "home"});
            })
            .catch((error) => {
              console.log(error);
              if (confirm("User doesn't exist. Want to register?")) {
                this.$router.push({name: "register"});
              }
            });
      }
    },
  },
};
</script>
