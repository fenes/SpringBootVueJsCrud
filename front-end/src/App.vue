<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
            <h1>Ferit Enes Güzel</h1>
            <h3>Vue SpringBoot PosgreSql</h3>
        </div>
        <nav>
            <router-link class="btn btn-primary" to="/">contact</router-link>
            <router-link class="btn btn-primary" to="/add">Add</router-link>
            <router-link class="btn btn-primary" to="/search">Search</router-link>
        </nav>
        <br/>
        <router-view/>
    </div>
</template>

<script>
export default {
  name: "app"
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
