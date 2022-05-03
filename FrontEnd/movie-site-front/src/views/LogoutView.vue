<template></template>

<script>
export default {
  name: "logout",

  data: () => ({}),
  created() {
    localStorage.clear();
    this.$root.$emit("changeLoggedIn");
    this.$router.push({name: "login"});
  },
};
</script>
