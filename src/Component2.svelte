<script>
  import Map from './Map2.svelte';

  let value;
</script>

<Map bind:value={value}/>

<div class='box'>
  <input bind:value={value}>
</div>
