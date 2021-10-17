<script>   
  import { onMount } from 'svelte';
  // import BINS from './bins.js';
  import BINS from './bins-new.js';
  export let value;
  export let details;
  export let objem;

  let bins = BINS.filter((bin) => bin.druh === value);

  let map;
  let circlesCenter;
  let circlesRadius;
  let isMounted = false;

  function getFillColor(bin) {
    if (objem && bin.druh !== 'tuky/oleje' && bin.druh !== 'textil' && bin.druh !== 'kovy') {
      let max;

      if (bin.druh === 'papír' || bin.druh === 'plasty') max = 20;
      else if (bin.druh === 'sklo barevné' || bin.druh === 'sklo bílé') max = 5;

      if (bin.objemObyvateleTyden === 'inf') {
        return 'hsl(120, 100%, 75%)';
      } else {
        let objemObyvateleNaTyden = bin.objemObyvateleTyden;

        if (objemObyvateleNaTyden > max) objemObyvateleNaTyden = max;

        const hue = objemObyvateleNaTyden / max * 120;
        
        return `hsl(${hue}, 100%, 50%)`;
      }
    } else {
      if (bin.druh === 'kovy') return '#9E9E9E';
      else if (bin.druh === 'papír') return '#2196F3';
      else if (bin.druh === 'plasty') return '#FF9800';
      else if (bin.druh === 'sklo barevné') return '#4CAF50';
      else if (bin.druh === 'sklo bílé') return '#000000';
      else if (bin.druh === 'textil') return '#673AB7';
      else if (bin.druh === 'tuky/oleje') return '#F44336';
    }
  }

  function getFillOpacity(bin) {
    if (objem && bin.druh !== 'tuky/oleje' && bin.druh !== 'textil' && bin.druh !== 'kovy') {
      return 0.33
    } else {
      let opacity;
      let defaultObjemObyvateleNaTyden

      if (bin.druh === 'papír' || bin.druh === 'plasty') defaultObjemObyvateleNaTyden = 20;
      else if (bin.druh === 'sklo barevné') defaultObjemObyvateleNaTyden = 5;
      else if (bin.druh === 'sklo bílé') defaultObjemObyvateleNaTyden = 0.5;
      else defaultObjemObyvateleNaTyden = 0;

      if (bin.objemObyvateleTyden === 'inf') {
        opacity = 0.25;
      } else {
        opacity = bin.objemObyvateleTyden / defaultObjemObyvateleNaTyden;

        if (opacity > 0.75) opacity = 0.75;
        else if (opacity < 0) opacity = 0;

        opacity = Math.abs(opacity - 1);
      } 

      return opacity;
    }
  }

  function getRadius(type) {
    if (type === 'papír' || type === 'plasty') return 100;
    else if (type === 'sklo barevné') return 150;
    else return 300;
  }

  $: if (value && (objem === false || objem === true) && isMounted) {
    circlesCenter.forEach((circle) => {
      circle.setMap(null);
      circle.addListener('click', () => {
        details = circle.details;
      });
    });
    circlesRadius.forEach((circle) => {
      circle.setMap(null);
      circle.addListener('click', () => {
        details = circle.details;
      });
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
          lat: bin.x,
          lng: bin.y,
        },
        radius: 20,
        details: { ...bin },
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
          lat: bin.x,
          lng: bin.y,
        },
        radius: getRadius(bin.druh),
        details: { ...bin },
      });
    });

    circlesCenter.forEach((circle) => {
      circle.setMap(map);
      circle.addListener('click', () => {
        details = circle.details;
      });
    });
    circlesRadius.forEach((circle) => {
      circle.setMap(map);
      circle.addListener('click', () => {
        details = circle.details;
      });
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
          lat: bin.x,
          lng: bin.y,
        },
        radius: 20,
        details: { ...bin }
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
          lat: bin.x,
          lng: bin.y,
        },
        radius: getRadius(bin.druh),
        details: { ...bin }
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
