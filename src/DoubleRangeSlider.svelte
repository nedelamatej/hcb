<script>
    export let start = 0;
    export let end = 1;
    export let lazyStart = 0;
    export let lazyEnd = 1;
    let leftHandle;
    let body;
    let slider;

    
    function draggable(node) {
        let x;
        let y;
        function handleMousedown(event) {
            if (event.type === "touchstart") {
                event = event.touches[0];
            }
            x = event.clientX;
            y = event.clientY;
            node.dispatchEvent(
                new CustomEvent("dragstart", {
                    detail: { x, y },
                })
            );
            window.addEventListener("mousemove", handleMousemove);
            window.addEventListener("mouseup", handleMouseup);
            window.addEventListener("touchmove", handleMousemove);
            window.addEventListener("touchend", handleMouseup);
        }
        function handleMousemove(event) {
            if (event.type === "touchmove") {
                event = event.changedTouches[0];
            }
            const dx = event.clientX - x;
            const dy = event.clientY - y;
            x = event.clientX;
            y = event.clientY;
            node.dispatchEvent(
                new CustomEvent("dragmove", {
                    detail: { x, y, dx, dy },
                })
            );
        }
        function handleMouseup(event) {
            x = event.clientX;
            y = event.clientY;
            node.dispatchEvent(
                new CustomEvent("dragend", {
                    detail: { x, y },
                })
            );
            window.removeEventListener("mousemove", handleMousemove);
            window.removeEventListener("mouseup", handleMouseup);
            window.removeEventListener("touchmove", handleMousemove);
            window.removeEventListener("touchend", handleMouseup);
        }
        node.addEventListener("mousedown", handleMousedown);
        node.addEventListener("touchstart", handleMousedown);
        return {
            destroy() {
                node.removeEventListener("mousedown", handleMousedown);
                node.removeEventListener("touchstart", handleMousedown);
            },
        };
    }
    function setHandlePosition(which) {
        return function (evt) {
            const { left, right } = slider.getBoundingClientRect();
            const parentWidth = right - left;
            const p = Math.min(
                Math.max((evt.detail.x - left) / parentWidth, 0),
                1
            );
            if (which === "start") {
                start = p;
                end = Math.max(end, p);
            } else {
                start = Math.min(p, start);
                end = p;
            }
        };
    }
    function setLazyHandlePosition(which) {
        return function (evt) {
            const { left, right } = slider.getBoundingClientRect();
            const parentWidth = right - left;
            const p = Math.min(
                Math.max((evt.detail.x - left) / parentWidth, 0),
                1
            );
            if (which === "start") {
                lazyStart = p;
                lazyEnd = Math.max(end, p);
            } else {
                lazyStart = Math.min(p, start);
                lazyEnd = p;
            }
        };
    }
</script>

<div class="double-range-container">
    <div class="slider" bind:this={slider}>
        <div
            class="body-slider"
            bind:this={body}
            style="
				left: {100 * start}%;
				right: {100 * (1 - end)}%;
			"
        />
        <div
            class="handle"
            bind:this={leftHandle}
            data-which="start"
            use:draggable
            on:dragmove|preventDefault|stopPropagation={setHandlePosition(
                "start"
            )}
            on:dragend|preventDefault|stopPropagation={setLazyHandlePosition(
                "start"
            )}
            style="
				left: {100 * start}%
			"
        />
        <div
            class="handle"
            data-which="end"
            use:draggable
            on:dragmove|preventDefault|stopPropagation={setHandlePosition(
                "end"
            )}
            on:dragend|preventDefault|stopPropagation={setLazyHandlePosition(
                "end"
            )}
            style="
				left: {100 * end}%
			"
        />
    </div>
</div>
