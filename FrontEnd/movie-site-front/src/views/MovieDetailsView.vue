<template>
  <h1>HELLO {{ movie.name }}</h1>
</template>

<script>
import axios from "axios";

export default {
  name: "movieDetails",
  data() {
    return {
      movie: null,
    };
  },
  created() {
    this.movie = this.$route.params.movie;
  },
};
</script>
