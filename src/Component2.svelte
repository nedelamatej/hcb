<script>
  import Map from './Map2.svelte';
  import DoubleRangeSlider from './DoubleRangeSlider.svelte';
  import distanceData from "./distance-data.js";
  import containerData from "./kontejnery.js";

  let typeFilter = "plasty";
  let minDistanceFilter = 0;
  let minDistanceDisplay = 0;

  let distanceArray = distanceData
    .filter(({ type }) => type == typeFilter)
    .map(({ distance }) => distance);

  $: distanceArray = distanceData
    .filter(({ type }) => type == typeFilter)
    .map(({ distance }) => distance);

  console.log(distanceArray)

  let maxDistance = Math.max(...distanceArray);

  function updateMinDistanceFilter(e) {
    let {value} = e.target
    minDistanceFilter = value
  }

</script>

<Map
  distanceData={distanceData}
  containerData={containerData}
  bind:typeFilter={typeFilter}
  bind:minDistanceFilter={minDistanceFilter}
/>

<div class='box'>
  <input
    type="range"
    max={maxDistance}
    bind:value={minDistanceFilter}
  >
  <DoubleRangeSlider  bind:end={minDistanceDisplay}  bind:lazyEnd={minDistanceFilter}/>
  <div>{minDistanceDisplay}</div>
</div>
