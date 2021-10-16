<script>
  import Map from './Map1.svelte';

  let value = 'plasty';
  let details = {};
  let objem = false;
</script>

<Map bind:value={value} bind:details={details} bind:objem={objem}/>

<div class='box'>
  <div class='box-title'>Třídění odpadu České Budějovice</div>
  <div class='box-team'>BROS | BRno OStrava</div>
  
  <div class='box-plasty' on:click={() => { value = 'plasty' }}
    class:selected="{value === 'plasty'}">Plasty</div>

  <div class='box-papir' on:click={() => { value = 'papír' }}
    class:selected="{value === 'papír'}">Papír</div>

  <div class='box-sklo-barevne' on:click={() => { value = 'sklo barevné' }}
    class:selected="{value === 'sklo barevné'}">Barevné sklo</div>

  <div class='box-sklo-bile' on:click={() => { value = 'sklo bílé' }}
    class:selected="{value === 'sklo bílé'}">Bílé sklo</div>

  <div class='box-tuky-oleje' on:click={() => { value = 'tuky/oleje' }}
    class:selected="{value === 'tuky/oleje'}">Tuky / oleje</div>

  <div class='box-textil' on:click={() => { value = 'textil' }}
    class:selected="{value === 'textil'}">Textil</div>

  <div class='box-kovy' on:click={() => { value = 'kovy' }}
    class:selected="{value === 'kovy'}">Kovy</div>

  <div class='box-objem' on:click={() => { objem = !objem }}
    class:selected="{objem}"
    class:disabled="{value === 'tuky/oleje' || value === 'textil' || value === 'kovy'}">
    Zobrazit objem [l] na obyvatele</div>

  {#if details.stanoviste !== undefined}
    <div class='box-details'>
      <div class='box-stanoviste'>{details.stanoviste}</div>
      <div class='box-x'>{details.x}</div>
      <div class='box-y'>{details.y}</div>
      <div class='box-druh'>{details.druh}</div>
      <div class='box-objem-x'>{details.objemNaObyvateleNaTyden}</div>
    </div>
  {/if}
</div>
