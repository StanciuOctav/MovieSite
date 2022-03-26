<template>
  <v-form
      ref="form"
      class="ml-6 mr-6"
  >
    <v-text-field
        v-model="name"
        label="Name"
        required
    ></v-text-field>
    <div v-if="invalidFields.name" style="color: red;">
      <p>Name cannot be empty</p>
    </div>

    <v-text-field
        v-model="age"
        label="Age"
        required
    ></v-text-field>
    <div v-if="invalidFields.age" style="color: red;">
      <p>Age field cannot be empty and must be over or equal to 18</p>
    </div>

    <v-text-field
        v-model="email"
        label="Email"
        type="email"
        required
    ></v-text-field>
    <div v-if="invalidFields.email" style="color: red;">
      <p>Email field cannot be empty</p>
    </div>

    <v-text-field
        v-model="password"
        label="Password"
        type="password"
        required
    ></v-text-field>
    <div v-if="invalidFields.password" style="color: red;">
      <p>Password cannot be empty</p>
    </div>

    <v-btn
        color="success"
        class="mr-4"
        @click="register"
    >
      Register
    </v-btn>

    <v-btn
        color="primary"
        class="mr-4"
        @click="redirectToLogin"
    >
      Back to Login
    </v-btn>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  name: 'Register',
  data() {
    return {
      bctx: "http://localhost:8080",
      fctx: window.location.protocol + "//" + window.location.host,
      name: "",
      age: "",
      email: "",
      password: "",
      invalidFields: {
        name: false,
        age: false,
        email: false,
        password: false
      }
    }
  },
  methods: {
    redirectToLogin() {
      window.location.href = this.fctx
    },
    register() {
      if (this.canRegister()) {
        const user = {
          age: this.age,
          email: this.email,
          name: this.name,
          password: this.password
        }
        axios.post(this.bctx + "/api/users", user)
            .then(response => {
              alert("Registration complete")
              window.location.href = this.fctx
            })
            .catch(error => {
              alert("User with the same email or password already exists")
              console.log(error)
            })
      } else {
        alert("Please complete all the fields correctly!")
      }
    },
    canRegister() {
      this.checkEmptyFields()
      return !this.invalidFields.name && !this.invalidFields.age && !this.invalidFields.email && !this.invalidFields.password
    },
    checkEmptyFields() {
      this.name === "" ? this.invalidFields.name = true : this.invalidFields.name = false
      this.age === "" || this.age < 18 ? this.invalidFields.age = true : this.invalidFields.age = false
      this.email === "" ? this.invalidFields.email = true : this.invalidFields.email = false
      this.password === "" ? this.invalidFields.password = true : this.invalidFields.password = false
    }
  }
}
</script>
