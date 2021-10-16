<script>   
  import { onMount } from 'svelte';
  import BINS from './bins.js';
  export let value;

  let bins = BINS.filter((bin) => bin.druh == 'plasty');

  let map;
  let circlesCenter;
  let circlesRadius;

  function getFillColor(type) {
    if (type === 'kovy') return '#9E9E9E';
    else if (type === 'papír') return '#2196F3';
    else if (type === 'plasty') return '#FF9800';
    else if (type === 'sklo barevné') return '#4CAF50';
    else if (type === 'sklo bílé') return '#000000';
    else if (type === 'textil') return '#9C27B0';
    else if (type === 'tuky/oleje') return '#F44336';
  }

  function getFillOpacity(bin) {
    let opacity;

    if (Number(bin.objem_na_obyvatele_na_tyden) === 0) {
      opacity = 0.25;
    } else {
      opacity = bin.objem_na_obyvatele_na_tyden / 20;

      if (opacity > 0.75) opacity = 0.75;
      else if (opacity < 0) opacity = 0;

      opacity = Math.abs(opacity - 1);
    }

    return opacity;
  }

  function getRadius(type) {
    if (type === 'kovy') return 400;
    else if (type === 'papír') return 100;
    else if (type === 'plasty') return 100;
    else if (type === 'sklo barevné') return 200;
    else if (type === 'sklo bílé') return 200;
    else if (type === 'textil') return 400;
    else if (type === 'tuky/oleje') return 400;
  }

  $: if (value) {
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
  });
</script>

<div id='map'></div>
