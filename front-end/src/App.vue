<template>
    <div id="app">
        <Navbar/>
        <div class="container">
            <router-view/>
        </div>
    </div>
</template>

<script>
    import Navbar from '@/components/Navbar'

    export default {
        name: "app",
        components: {
            Navbar
        }
    };
</script>

<style>
    .site-info {
        color: blue;
        margin-bottom: 20px;
    }

    .btn-primary {
        margin-right: 5px;
    }

    .container-fluid {
        text-align: center;
    }
</style>
