<script>
  import Map from './Map1.svelte';

  let value = 'plasty';
  let details = {};
  let objem = false;

  $: if (value) details = {};
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
      <div class='box-stanoviste'
        class:box-stanoviste-plasty="{details.druh === 'plasty'}"
        class:box-stanoviste-papir="{details.druh === 'papír'}"
        class:box-stanoviste-sklo-barevne="{details.druh === 'sklo barevné'}"
        class:box-stanoviste-sklo-bile="{details.druh === 'sklo bílé'}"
        class:box-stanoviste-tuky-oleje="{details.druh === 'tuky/oleje'}"
        class:box-stanoviste-textil="{details.druh === 'textil'}"
        class:box-stanoviste-kovy="{details.druh === 'kovy'}">{details.stanoviste}</div>
      <div class='box-souradnice'>{Number(details.x.replace(',', '.')).toFixed(6)}N {Number(details.y.replace(',', '.')).toFixed(6)}E</div>
      <div class='box-druh'>Druh odpadu: &nbsp; <b> {details.druh} </b></div>
      {#if Number(details.objemNaObyvateleNaTyden) !== 0}
        <div class='box-objem-x'>Objem na obyvatele: &nbsp; <b> {details.objemNaObyvateleNaTyden.toFixed(2)} </b></div>
      {/if}
    </div>
  {/if}
</div>
