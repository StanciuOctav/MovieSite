<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
            alt="Vuetify Logo"
            class="shrink mr-2"
            contain
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
            transition="scale-transition"
            width="40"
        />

        <h1>Movie Site</h1>
      </div>
      <v-btn @click="addNewMovie" class="my-4 mx-4" color="success" rounded>
        <v-icon left> mdi-pencil</v-icon>
        Add new Movie
      </v-btn>
      <v-btn @click="logout" class="my-4 mx-4" color="error" rounded>
        <v-icon left> mdi-pencil</v-icon>
        Logout
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
  methods: {
    addNewMovie: function () {
      this.$router.push({name: "addMovie"});
    },
    logout() {
      this.$router.push({name: "logout"});
    },
  },
};
</script>
