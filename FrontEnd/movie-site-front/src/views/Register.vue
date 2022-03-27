<template>
  <v-form v-model="valid" ref="form" class="ml-6 mr-6">
    <v-text-field v-model="name" :rules="nameRules" label="Name" required>
    </v-text-field>

    <v-text-field
        v-model="age"
        :rules="ageRules"
        label="Age"
        required
    ></v-text-field>

    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="Email"
        type="email"
        required
    ></v-text-field>

    <v-text-field
        v-model="password"
        :rules="nameRules"
        label="Password"
        type="password"
        required
    ></v-text-field>

    <v-btn :disabled="!valid" color="success" class="mr-4" @click="register">
      Register
    </v-btn>

    <v-btn
        color="primary"
        class="mr-4"
        @click="$router.push({ name: 'login' })"
    >
      Back to Login
    </v-btn>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      bctx: "http://localhost:8080",
      name: "",
      age: "",
      email: "",
      password: "",
      valid: true,
      nameRules: [(v) => !!v || "This field is mandatory"],
      ageRules: [
        (v) => !!v || "This field is mandatory",
        (v) =>
            (v && v >= 18 && v <= 90) ||
            "You must have between 18 and 90 years old",
      ],
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) =>
            /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
                v
            ) || "E-mail must be valid",
      ],
    };
  },
  methods: {
    validate() {
      return this.$refs.form.validate();
    },
    register() {
      if (this.validate()) {
        const user = {
          age: this.age,
          email: this.email,
          name: this.name,
          password: this.password,
        };
        axios
            .post(this.bctx + "/api/users", user)
            .then((response) => {
              alert("Registration complete");
              this.$router.push({name: "login"});
            })
            .catch((error) => {
              alert("User with the same email or password already exists");
              console.log(error);
            });
      } else {
        alert("Please complete all the fields correctly!");
      }
    },
  },
};
</script>
