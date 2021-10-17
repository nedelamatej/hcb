<script>
    import { onMount } from "svelte";
    export let distanceData;
    export let containerData;
    export let typeFilter;
    export let minDistanceFilter;
    export let maxDistanceFilter;
    export let showTrashcans;

    let map;
    let circles = [];
    let containers= [];

    $: filter(typeFilter, minDistanceFilter, maxDistanceFilter, showTrashcans)
        
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

    function filter(type, minDistance, maxDistance, showTrashcans) {
        if (circles.length == 0 && containers.length == 0) return
        distanceData.forEach((d, id) => {
            if (d.type == type && d.distance >= minDistance && d.distance <= maxDistance) {
                /* if(circles[id]?.getMap() === null) {
                    console.log("not rendered");
                    circles[id]?.setMap(map)
                } */
                    circles[id]?.setOptions({fillOpacity: 0.35/* visible: true */});
            } else {
                    circles[id]?.setOptions({fillOpacity: 0/* visible: false */});
            }
        });
        containerData.forEach((d, id) => {
            if (d.type == type && showTrashcans) {
                containers[id]?.setOptions({fillOpacity: 1/* visible: true */});
            } else {
                containers[id]?.setOptions({fillOpacity: 0/* visible: false */});
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
                fillOpacity:  0,
                center: { lat: d.lat, lng: d.lng },
                radius: Math.sqrt(d.count) * 5,
                map/* : (d.type == typeFilter && d.distance >= minDistanceFilter && d.distance <= maxDistanceFilter) ? map : null */
            });
            circles.push(circle)
        });
        containerData.forEach((d) => {
            let container = new google.maps.Circle({
                strokeWeight: 0,
                fillColor: "black",
                fillOpacity: 0,
                center: { lat: d.lat, lng: d.lng },
                radius: 15,
                map
            });
            containers.push(container)
        });

        filter(typeFilter, minDistanceFilter, maxDistanceFilter, showTrashcans)
    });
</script>

<div id="map" />
