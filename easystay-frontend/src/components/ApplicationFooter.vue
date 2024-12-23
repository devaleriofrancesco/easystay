<template>
  <footer class="pt-3 container-fluid bg-dark text-white">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <p class="text-center">
            <img src="@/assets/images/logo2.png" alt="EasyStay" height="100" />
          </p>
          <p class="text-center mt-3">
            EasyStay - la scelta semplice per un soggiorno indimenticabile.
          </p>
          <p class="text-center mt-3">Via delle Magnolie, 5 - 70124 Bari (BA)</p>
          <p class="text-center mt-3">
            Tel. <a href="tel:+390801234567"> +39 080 1234567</a> | E-Mail:
            <a href="mailto:info@easystay.it">info@easystay.it</a>
          </p>
          <hr />
          <p class="text-center">© {{currentYear}} EasyStay</p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script lang="ts">
  export default {
    name: 'ApplicationFooter',
    computed: {
      currentYear() {
        return new Date().getFullYear()
      },
    },
  }
</script>

<style scoped lang="scss">
a {
  color: white;
  text-decoration: none;

  &:hover {
    color: white;
    text-decoration: underline;
  }
}
</style>
