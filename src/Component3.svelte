<script>
    import Map from "./Map3.svelte";
    import DoubleRangeSlider from "./DoubleRangeSlider.svelte";

    export let page;
    export let distanceData;
    export let containerData;

    let typeFilter = "plasty";
    let showTrashcans = true
    let start = 0;
    let lazyStart = 0;
    let end = 1;
    let lazyEnd = 1;

    let distanceArray = distanceData.reduce((a, c) => {
        return {
            "dist_kovy": Math.max(a["dist_kovy"], c["dist_kovy"]),
            "dist_papír": Math.max(a["dist_papír"], c["dist_papír"]),
            "dist_plasty": Math.max(a["dist_plasty"], c["dist_plasty"]),
            "dist_sklo barevné": Math.max(a["dist_sklo barevné"], c["dist_sklo barevné"]),
            "dist_sklo bílé": Math.max(a["dist_sklo bílé"], c["dist_sklo bílé"]),
            "dist_textil": Math.max(a["dist_textil"], c["dist_textil"]),
            "dist_tuky/oleje": Math.max(a["dist_tuky/oleje"], c["dist_tuky/oleje"]),
        }
    });
    console.log(distanceArray);
    /* .filter(({ type }) => type == typeFilter)
    .map(({ distance }) => distance); */

    $: maxDistance = distanceArray[`dist_${typeFilter}`];
    console.log(lazyStart);

    const step = 10;
    $: minDistanceFilter = Math.floor((maxDistance * lazyStart) / step) * 10;
    $: minDistanceDisplay = Math.floor((maxDistance * start) / step) * 10;
    $: maxDistanceFilter = Math.floor((maxDistance * lazyEnd) / step) * 10;
    $: maxDistanceDisplay = Math.floor((maxDistance * end) / step) * 10;

    function updateMinDistanceFilter(e) {
        let { value } = e.target;
        minDistanceFilter = value;
    }
</script>

<Map
    {distanceData}
    {containerData}
    bind:typeFilter
    bind:minDistanceFilter
    bind:maxDistanceFilter
    bind:showTrashcans
/>

<div class="box">
    <div class='box-2'>
      <div class="box-title">Třídění odpadu České Budějovice</div>
      <div class='box-team'>Jihočeský hackaton 2021</div>
      <div class='component-box-title'><i>Zobrazení:</i></div>
      <div
          class="box-objem"
          style="margin: 0px"
          on:click={() => {
              page = "trashAccessibility";
          }}
      >
          Obyvatelé (docházková vzdálenost)
      </div>
      <div
          class="box-objem  selected"
          style="margin: 0px"
          on:click={() => {
              page = "trashPoints";
          }}
      >
          Adresní body (vzdušná vzdálenost)
      </div>
      <div
          class="box-objem"
          style="margin: 0px"
          on:click={() => {
              page = "trashOverview";
          }}
      >
          Kontejnery (vzdušná vzdálenost)
      </div>

      <div class='component-box-title'><i>Typ odpadu:</i></div>
      <div
          class="box-plasty"
          on:click={() => {
              typeFilter = "plasty";
          }}
          class:selected={typeFilter === "plasty"}
      >
          Plasty
      </div>

      <div
          class="box-papir"
          on:click={() => {
              typeFilter = "papír";
          }}
          class:selected={typeFilter === "papír"}
      >
          Papír
      </div>

      <div
          class="box-sklo-barevne"
          on:click={() => {
              typeFilter = "sklo barevné";
          }}
          class:selected={typeFilter === "sklo barevné"}
      >
          Barevné sklo
      </div>

      <div
          class="box-sklo-bile"
          on:click={() => {
              typeFilter = "sklo bílé";
          }}
          class:selected={typeFilter === "sklo bílé"}
      >
          Bílé sklo
      </div>

      <div
          class="box-tuky-oleje"
          on:click={() => {
              typeFilter = "tuky/oleje";
          }}
          class:selected={typeFilter === "tuky/oleje"}
      >
          Tuky/oleje
      </div>

      <div
          class="box-textil"
          on:click={() => {
              typeFilter = "textil";
          }}
          class:selected={typeFilter === "textil"}
      >
          Textil
      </div>

      <div
          class="box-kovy"
          on:click={() => {
              typeFilter = "kovy";
          }}
          class:selected={typeFilter === "kovy"}
      >
          Kovy
      </div>

      <div
          class="box-objem"
          on:click={() => {
              showTrashcans = !showTrashcans;
          }}
          class:selected={showTrashcans}
      >
          Zobrazit kontejnery
      </div>

      <!-- <input type="range" max={maxDistance} bind:value={minDistanceFilter} /> -->
      <div class="rangeslider" style="margin-bottom: 38px">
        <div><i><b>Filtr vzdálenosti odpadu:</b></i></div>
        <DoubleRangeSlider bind:start bind:lazyStart bind:end bind:lazyEnd />
        <div class="rangeslider__values">
            <div>{minDistanceDisplay} m</div>
            <div>{maxDistanceDisplay} m</div>
        </div>
      </div>
  </div>
</div>
