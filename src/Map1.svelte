<script>   
  import { onMount } from 'svelte';
  import BINS from './bins.js';
  export let value;
  export let details;
  export let objem;

  $: console.log(objem);

  let bins = BINS.filter((bin) => bin.druh === value);

  let map;
  let circlesCenter;
  let circlesRadius;
  let isMounted = false;

  function getFillColor(bin) {
    if (objem) {
      let max;

      if (bin.druh === 'papír' || bin.druh === 'plasty') max = 20;
      else if (bin.druh === 'sklo barevné' || bin.druh === 'sklo bílé') max = 5;

      let objemObyvateleNaTyden = bin.objem_na_obyvatele_na_tyden;

      if (objemObyvateleNaTyden > max) objemObyvateleNaTyden = max;

      const hue = objemObyvateleNaTyden / max * 120;
      
      return `hsl(${hue},100%,50%)`;
    } else {
      if (bin.druh === 'kovy') return '#9E9E9E';
      else if (bin.druh === 'papír') return '#2196F3';
      else if (bin.druh === 'plasty') return '#FF9800';
      else if (bin.druh === 'sklo barevné') return '#4CAF50';
      else if (bin.druh === 'sklo bílé') return '#000000';
      else if (bin.druh === 'textil') return '#9C27B0';
      else if (bin.druh === 'tuky/oleje') return '#F44336';
    }
  }

  function getFillOpacity(bin) {
    if (objem) {
      return 0.25
    } else {
      let opacity;
      let defaultObjemObyvateleNaTyden

      if (bin.type === 'papír' || bin.type === 'plasty') defaultObjemObyvateleNaTyden = 20;
      else if (bin.type === 'sklo barevné' || bin.type === 'sklo bílé') defaultObjemObyvateleNaTyden = 5;
      else defaultObjemObyvateleNaTyden = 0;

      if (Number(bin.objem_na_obyvatele_na_tyden) === 0) {
        opacity = 0.25;
      } else {
        opacity = bin.objem_na_obyvatele_na_tyden / defaultObjemObyvateleNaTyden;

        if (opacity > 0.75) opacity = 0.75;
        else if (opacity < 0) opacity = 0;

        opacity = Math.abs(opacity - 1);
      } 

      return opacity;
    }
  }

  function getRadius(type) {
    if (type === 'papír' || type === 'plasty') return 100;
    else if (type === 'sklo barevné' || type === 'sklo bílé') return 150;
    else return 300;
  }

  $: if (value && (objem === false || objem === true) && isMounted) {
    circlesCenter.forEach((circle) => {
      circle.setMap(null);
    });
    circlesRadius.forEach((circle) => {
      circle.setMap(null);
    });

    bins = BINS.filter((bin) => bin.druh === value);

    circlesCenter = bins.map((bin) => {
      return new google.maps.Circle({
        strokeColor: '#FFFFFF',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: getFillColor(bin),
        fillOpacity: 1,
        center: {
          lat: Number(bin.x.replace(',', '.')),
          lng: Number(bin.y.replace(',', '.')),
        },
        radius: 20,
        details: {
          stanoviste: bin.stanoviste,
          x: bin.x,
          y: bin.y,
          druh: bin.druh,
          objemNaObyvateleNaTyden: bin.objem_na_obyvatele_na_tyden,
        },
      });
    });

    circlesRadius = bins.map((bin) => {
      return new google.maps.Circle({
        strokeColor: '#FFFFFF',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: getFillColor(bin),
        fillOpacity: getFillOpacity(bin),
        center: {
          lat: Number(bin.x.replace(',', '.')),
          lng: Number(bin.y.replace(',', '.')),
        },
        radius: getRadius(bin.druh),
      });
    });

    circlesCenter.forEach((circle) => {
      circle.setMap(map);
      circle.addListener("click", () => {
        details = circle.details;
      });
    });
    circlesRadius.forEach((circle) => {
      circle.setMap(map);
    });
  }
   
  onMount(async () => {
    map = new google.maps.Map(document.getElementById('map'), {
      center: { lat: 48.9744689, lng: 14.4743419 },
      zoom: 14,
      disableDefaultUI: true,
      zoomControl: true,
    });

    circlesCenter = bins.map((bin) => {
      return new google.maps.Circle({
        strokeColor: '#FFFFFF',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: getFillColor(bin.druh),
        fillOpacity: 1,
        center: {
          lat: Number(bin.x.replace(',', '.')),
          lng: Number(bin.y.replace(',', '.')),
        },
        radius: 20,
      });
    });

    circlesRadius = bins.map((bin) => {
      return new google.maps.Circle({
        strokeColor: '#FFFFFF',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: getFillColor(bin.druh),
        fillOpacity: getFillOpacity(bin),
        center: {
          lat: Number(bin.x.replace(',', '.')),
          lng: Number(bin.y.replace(',', '.')),
        },
        radius: getRadius(bin.druh),
      });
    });

    circlesCenter.forEach((circle) => {
      circle.setMap(map);
    });
    circlesRadius.forEach((circle) => {
      circle.setMap(map);
    });

    isMounted = true;
  });
</script>

<div id='map'></div>
