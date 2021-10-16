<script>
  import Map from './Map.svelte';
  export let ready;

  let value;
</script>

<svelte:head>
  <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCo8Q4zNi5sTwNLlr6TY3OqYC3a_kNROKA&callback=initMap&v=weekly' async></script>
</svelte:head>

{#if ready}
  <Map bind:value={value}/>

  <div class='box'>
    <input bind:value={value}>
  </div>
{/if}
