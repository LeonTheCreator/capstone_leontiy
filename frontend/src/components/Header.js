import styled from 'styled-components/macro'

export default function Header({ title, ...props }) {
  return (
    <Wrapper {...props}>
      <h1>{title}</h1>
    </Wrapper>
  )
}

const Wrapper = styled.header`
  width: 100%;
  text-align: center;
  background: var(--background-dark);
  color: var(--accent);
  display: grid;
  place-items: center;
  grid-gap: var(--size-xxl);
  padding: var(--size-xl);
  height: 100%;
  width: 100%;
  overflow-y: scroll;
`
