<template>
    <div>
            <h3 id="search2">Search</h3>
            <v-text-field
            label="Name"
            v-model="patientName"
            filled
            ></v-text-field>
            <v-text-field
            label="Surname"
            v-model="surname"
            filled
            ></v-text-field>
            <button type="button" v-on:click="search()">Pretrazi</button>
        </div>
</template>

<script>


export default ({
    name:  "PatientSearch",
    data: () => ({
      patientName: null,
      surname: null
    }),
    methods: {
        search: function(){
            this.$emit('clicked',{name:this.patientName,surname:this.surname})
        }
    }
})
</script>
