<script>
    import { onMount } from "svelte";
    export let distanceData;
    export let containerData;
    export let typeFilter;
    export let minDistanceFilter;
    export let maxDistanceFilter;

    let map;
    let circles = [];
    let containers= [];

    $: filter(typeFilter, minDistanceFilter, maxDistanceFilter)
        
        /* circlesCenter.forEach((circle) => {
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
      }); */

    function filter(type, minDistance, maxDistance) {
        distanceData.forEach((d, id) => {
            if (d.type == type && d.distance >= minDistance && d.distance <= maxDistance) {
                circles[id]?.setOptions({fillOpacity: 0.35});
            } else {
                circles[id]?.setOptions({fillOpacity: 0});
            }
        });
        containerData.forEach((d, id) => {
            if (d.type == type) {
                containers[id]?.setOptions({fillOpacity: 1});
            } else {
                containers[id]?.setOptions({fillOpacity: 0});
            }
        });
    }

    function getGradientColor(value, min = 0, max = 300) {
        const gradinetEndHue = 120;
        let hue = 0;
        if (value < 300) {
            const range = max - min;
            let relativeValue = (value - min) / range;
            hue = 120 - relativeValue * gradinetEndHue;
        }
        return `hsl(${hue},100%,50%)`;
    }

    onMount(async () => {
        map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: 48.9744689, lng: 14.4743419 },
            zoom: 14,
            disableDefaultUI: true,
            zoomControl: true,
        });

        distanceData.forEach((d) => {
            const circle = new google.maps.Circle({
                strokeColor: "#FF0000",
                strokeOpacity: 0.8,
                strokeWeight: 0,
                fillColor: getGradientColor(d.distance),
                fillOpacity: 0,
                center: { lat: d.lat, lng: d.lng },
                radius: Math.sqrt(d.count) * 5,
                map
            });
            circles.push(circle)
        });
        containerData.forEach((d) => {
            let container = new google.maps.Circle({
                strokeWeight: 0,
                fillColor: "blue",
                fillOpacity: 0,
                center: { lat: d.lat, lng: d.lng },
                radius: 25,
                map
            });
            containers.push(container)
        });

        filter(typeFilter, minDistanceFilter, maxDistanceFilter)
    });
</script>

<div id="map" />
