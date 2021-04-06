<template>
  <div>
    <v-combobox v-model="select" :items="items"></v-combobox>
    <button type="button" v-on:click="sorting()">Sort</button>
  </div>
</template>

<script>
export default {
  name: "PatientSort",
  data: () => ({
    select: "",
    items: ["NameAsc","NameDesc","SurnameAsc","SurnameDesc"]
  }),
  methods: {
    sorting: function () {
      this.$emit("clicked", this.select);
    },
  },
};
</script>