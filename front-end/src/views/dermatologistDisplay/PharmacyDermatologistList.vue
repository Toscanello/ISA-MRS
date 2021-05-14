<template>
<div>
  <pharmacy-admin-home></pharmacy-admin-home>
  <dermatologist-list context="PHARMA"></dermatologist-list>
</div>
</template>

<script>
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import DermatologistList from './DermatologistList.vue'
export default {
  components: { DermatologistList, PharmacyAdminHome },
  name: 'PharmacyDermatologistList'
}
</script>

<style>

</style>