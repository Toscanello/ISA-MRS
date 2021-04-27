<template>
  <v-container>
    <v-row>
      <v-col>
        <v-combobox v-model="select" :items="items" style="margin-top: 15px;"></v-combobox>
      </v-col>
      <v-col>
        <button type="button" v-on:click="sorting()" style="margin-top: 20px;margin-right: 150px;">Sort</button>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "PatientSort",
  data: () => ({
    select: "NameAsc",
    items: ["NameAsc", "NameDesc", "SurnameAsc", "SurnameDesc"],
  }),
  methods: {
    sorting: function () {
      this.$emit("clicked", this.select);
    },
  },
};
</script>
<style scoped>
</style>