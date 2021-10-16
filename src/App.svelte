<script>
  import Component1 from './Component1.svelte';
  import Component2 from './Component2.svelte';

  export let ready;
</script>

<svelte:head>
  <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCo8Q4zNi5sTwNLlr6TY3OqYC3a_kNROKA&callback=initMap&v=weekly' async></script>
</svelte:head>

{#if ready}
  <Component1/>
{/if}
