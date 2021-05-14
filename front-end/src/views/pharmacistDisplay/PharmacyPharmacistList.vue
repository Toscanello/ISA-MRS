<template>
  <div>
      <pharmacy-admin-home></pharmacy-admin-home>
      <pharmacist-list context="PHARMA"></pharmacist-list>
  </div>
</template>

<script>
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import PharmacistList from './PharmacistList.vue'
export default {
  components: { PharmacyAdminHome, PharmacistList },
  name: 'PharmacyPharmacistList',
}
</script>

<style>

</style>