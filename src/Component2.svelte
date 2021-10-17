<script>
    import Map from "./Map2.svelte";
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
        if (a.hasOwnProperty(c.type)) {
            return {
                ...a,
                [c.type]: Math.max(c.distance, a[c.type]),
            };
        } else {
            return {
                ...a,
                [c.type]: c.distance,
            };
        }
    }, {});
    console.log(distanceArray);
    /* .filter(({ type }) => type == typeFilter)
    .map(({ distance }) => distance); */

    $: maxDistance = distanceArray[typeFilter];
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
    <div class="box-title">Třídění odpadu České Budějovice</div>
    <div class="box-team">BROS | BRno OStrava</div>

    <div class="switch">
        <div
            class="switch__card active"
            on:click={() => {
                page = "trashAccessibility";
            }}
        >
            Dostupnost odpadů
        </div>
        <div
            class="switch__card"
            on:click={() => {
                page = "trashOverview";
            }}
        >
            Přehled odpadů
        </div>
    </div>

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
        Tuky / oleje
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

    <!-- <input type="range" max={maxDistance} bind:value={minDistanceFilter} /> -->
    <div class="rangeslider">
        <div>Filtr vzdálenosti odpadu</div>
        <DoubleRangeSlider bind:start bind:lazyStart bind:end bind:lazyEnd />
        <div class="rangeslider__values">
            <div>{minDistanceDisplay} m</div>
            <div>{maxDistanceDisplay} m</div>
        </div>
    </div>
    <div
        class="box-objem"
        on:click={() => {
            showTrashcans = !showTrashcans;
        }}
        class:selected={showTrashcans}
        class:disabled={typeFilter === "tuky/oleje" ||
            typeFilter === "textil" ||
            typeFilter === "kovy"}
    >
        Zobrazit kontejnery
    </div>
</div>
