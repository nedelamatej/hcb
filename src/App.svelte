<script>
  import Component1 from './Component1.svelte';
  import Component2 from './Component2.svelte';

  export let ready;
</script>

<svelte:head>
  <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCgSqhWK08M-_3baUPR4kJvl02r4TZh6ew&callback=initMap&v=weekly' async></script>
</svelte:head>

{#if ready}
  <Component1/>
{/if}
