<script>
  import Map from './Map1.svelte';

  export let page

  let value = 'plasty';
  let details = {};
  let objem = true;

  $: if (value) details = {};
</script>

<Map bind:value={value} bind:details={details} bind:objem={objem}/>

<div class='box'>
<div class='box-2'>
  <div class='box-title'>Třídění odpadu České Budějovice</div>
  <div class='box-team'>Jihočeský hackaton 2021</div>

  <div class='component-box-title'><i>Zobrazení:</i></div>
  <div
      class="box-objem"
      style="margin: 0px"
      on:click={() => {
          page = "trashAccessibility";
      }}
  >
      Obyvatelé (docházková vzdálenost)
  </div>
  <div
    class="box-objem"
    style="margin: 0px"
    on:click={() => {
        page = "trashPoints";
    }}
  >
    Adresní body (vzdušná vzdálenost)
  </div>
  <div
      class="box-objem selected"
      style="margin: 0px"
      on:click={() => {
          page = "trashOverview";
      }}
  >
      Kontejnery (vzdušná vzdálenost)
  </div>

  <div class='component-box-title'><i>Typ odpadu:</i></div>
  <div class='box-plasty' on:click={() => { value = 'plasty' }}
    class:selected="{value === 'plasty'}">Plasty</div>

  <div class='box-papir' on:click={() => { value = 'papír' }}
    class:selected="{value === 'papír'}">Papír</div>

  <div class='box-sklo-barevne' on:click={() => { value = 'sklo barevné' }}
    class:selected="{value === 'sklo barevné'}">Barevné sklo</div>

  <div class='box-sklo-bile' on:click={() => { value = 'sklo bílé' }}
    class:selected="{value === 'sklo bílé'}">Bílé sklo</div>

  <div class='box-tuky-oleje' on:click={() => { value = 'tuky/oleje' }}
    class:selected="{value === 'tuky/oleje'}">Tuky/oleje</div>

  <div class='box-textil' on:click={() => { value = 'textil' }}
    class:selected="{value === 'textil'}">Textil</div>

  <div class='box-kovy' on:click={() => { value = 'kovy' }}
    class:selected="{value === 'kovy'}">Kovy</div>

  <div class='box-objem' style="margin-bottom: 38px;" on:click={() => { objem = !objem }}
    class:selected="{objem}">Zobrazit objem na obyvatele (l)</div>

  {#if details.stanoviste !== undefined}
    <div class='box-details' style="margin-bottom: 38px; margin-top: 0px;">
      <div class='box-stanoviste'
        class:box-stanoviste-plasty="{details.druh === 'plasty'}"
        class:box-stanoviste-papir="{details.druh === 'papír'}"
        class:box-stanoviste-sklo-barevne="{details.druh === 'sklo barevné'}"
        class:box-stanoviste-sklo-bile="{details.druh === 'sklo bílé'}"
        class:box-stanoviste-tuky-oleje="{details.druh === 'tuky/oleje'}"
        class:box-stanoviste-textil="{details.druh === 'textil'}"
        class:box-stanoviste-kovy="{details.druh === 'kovy'}">{details.stanoviste}</div>
      <div class='box-souradnice'>{details.x.toFixed(6)}N {details.y.toFixed(6)}E</div>
      <div class='box-druh'>Druh:&nbsp;<b>{details.druh} </b></div>
      <div class='box-pocet'>Počet (ks):&nbsp;<b>{details.pocet} </b></div>
      <div class='box-objem-kontejneru'>Objem celkem (l):&nbsp;<b>{details.objem} </b></div>
      <div class='box-typ'>Typ:&nbsp;<b>{details.typ} </b></div>
      {#if details.cetnost !== 'neurčeno'}
        <div class='box-objem-cetnost'>Svoz:&nbsp;<b>{details.cetnost}</b> ({details.den})</div>
      {/if}
      {#if Number(details.obyvatel) !== 0}
        <div class='box-obyvatel'>Obyvatel:&nbsp;<b>{details.obyvatel} </b></div>
      {/if}
      {#if details.objemObyvateleTyden !== 'inf'}
      <div class='box-objem-obyvatele-tyden'>Objem na obyvatele (l):&nbsp;<b>{details.objemObyvateleTyden.toFixed(2)} </b></div>
      {/if}
    </div>
  {/if}
</div>
</div>
