<script>
    import { onMount } from "svelte";
    export let distanceData;
    export let containerData;
    export let typeFilter;
    export let minDistanceFilter;

    let map;
    let circles;
    let containers;

    $: if (minDistanceFilter) {
        filter(typeFilter, minDistanceFilter)
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
    }

    function filter(type, minDistance) {
        console.log(type, minDistance);
        distanceData.forEach((d, id) => {
            if (d.type == type && d.distance >= minDistance) {
                circles[id].setMap(map);
            } else {
                circles[id].setMap(null);
            }
        });
        containerData.forEach((d, id) => {
            if (d.type == type) {
                containers[id].setMap(map);
            } else {
                containers[id].setMap(null);
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

        circles = distanceData.map((d) => {
            return new google.maps.Circle({
                strokeColor: "#FF0000",
                strokeOpacity: 0.8,
                strokeWeight: 0,
                fillColor: getGradientColor(d.distance),
                fillOpacity: 0.35,
                center: { lat: d.lat, lng: d.lng },
                radius: Math.sqrt(d.count) * 5,
            });
        });
        containers = containerData.map((d) => {
            return new google.maps.Circle({
                strokeWeight: 0,
                fillColor: "blue",
                fillOpacity: 1,
                center: { lat: d.lat, lng: d.lng },
                radius: 25,
            });
        });

        filter(typeFilter, minDistanceFilter)
    });
</script>

<div id="map" />
