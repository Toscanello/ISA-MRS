<template>
      <div>
          Sort
        <v-combobox @change="sorting()" v-model="select" :items="items" style="margin: 0;
  padding: 0;"></v-combobox>
      </div>
  
</template>

<script>
export default {
  data: () => ({
    select: "RatingAsc",
    items: ["RatingAsc", "RatingDesc", "PriceAsc", "PriceDesc"],
  }),
  methods: {
    sorting: function () {
        console.log(this.select)
      this.$emit("clicked", this.select);
    }
  },
};
</script>
<style scoped>
</style>