<script>
  import Component1 from "./Component1.svelte";
  import Component2 from "./Component2.svelte";
  import distanceData from "./distance-data.js";
  import containerData from "./kontejnery.js";

  export let ready;
  let page = "trashAccessibility";
</script>

<svelte:head>
  <script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgSqhWK08M-_3baUPR4kJvl02r4TZh6ew&callback=initMap&v=weekly"
    async></script>
</svelte:head>

{#if ready}
  {#if page == "trashOverview"}
    <Component1 bind:page />
  {/if}
  {#if page == "trashAccessibility"}
    <Component2 bind:page {distanceData} {containerData} />
  {/if}
{/if}
