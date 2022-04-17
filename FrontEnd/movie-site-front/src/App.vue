<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center" style="margin-right: auto">
        <h1>Movie Site</h1>
        <v-btn @click="addNewMovie" class="my-4 mx-4" color="success" rounded>
          <v-icon left> mdi-pencil</v-icon>
          Add new Movie
        </v-btn>
      </div>

      <v-btn @click="redirectHome()">
        <v-icon>mdi-home</v-icon>
      </v-btn>

      <v-menu
          v-for="([text, rounded], index) in btns"
          :key="text"
          :rounded="rounded"
          offset-y
      >
        <template v-slot:activator="{ attrs, on }">
          <v-btn
              :color="colors[index]"
              class="white--text ma-5"
              v-bind="attrs"
              v-on="on"
          >
            <v-icon> mdi-account</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index" link>
            <v-list-item-title
                @click="showClicked(item.title)"
                v-text="item.title"
            ></v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
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
    btns: [["Custom", "b-xl"]],
    colors: ["deep-purple accent-4"],
    items: [{title: "Profile details"}, {title: "Logout"}],
  }),
  methods: {
    // Profile dropdown
    showClicked(name) {
      if (name === "Logout") this.logout();
      if (name === "Profile details") {
        console.log("Redirecting to profile page");
      }
    },
    redirectHome() {
      this.$router.push({name: "home"});
    },
    addNewMovie() {
      this.$router.push({name: "addMovie"});
    },
    logout() {
      this.$router.push({name: "logout"});
    },
  },
};
</script>
